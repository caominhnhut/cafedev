package com.cafedev.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cafedev.common.MessageConst;
import com.cafedev.dto.ExaminationDTO;
import com.cafedev.dto.ExaminationDetailDTO;
import com.cafedev.dto.UploadFileResponseDTO;
import com.cafedev.model.Examination;
import com.cafedev.model.ExaminationUser;
import com.cafedev.model.User;
import com.cafedev.service.ExaminationService;
import com.cafedev.service.FileStorageService;

@RestController
@RequestMapping(value = "/rest/examination/")
public class ExaminationController {
	
	Logger logger = LoggerFactory.getLogger(ExaminationController.class);

	@Autowired
	private ExaminationService examinationService;
	
	@Autowired
	private FileStorageService fileStorageService;


	@RequestMapping(method = RequestMethod.GET, value = "find-by-user")
	public ResponseEntity<List<ExaminationDTO>> findByOwnerId() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		
		List<Examination> examinations = examinationService.findByUserId(user.getId());
		if (examinations.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		List<ExaminationDTO> examinationDTOs = new ArrayList<ExaminationDTO>();
		for (Examination examination : examinations) {
			ExaminationDTO examinationDTO = new ExaminationDTO();
			examinationDTO.copyFrom(examination);
			examinationDTOs.add(examinationDTO);
		}
		
		return new ResponseEntity<List<ExaminationDTO>>(examinationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "find-by-user-exam")
	public ResponseEntity<ExaminationDetailDTO> findByUserAndExam(
			@PathParam("idUser") Long userId, @PathParam("idExam") Long examId) {		
		ExaminationUser examinationUser = examinationService.findByUserAndExam(
				userId, examId);
		ExaminationDetailDTO examinationDetailDTO = new ExaminationDetailDTO();
		if (examinationDetailDTO == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		examinationDetailDTO.copyFrom(examinationUser);
		return new ResponseEntity<ExaminationDetailDTO>(examinationDetailDTO,
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="push-exercise", consumes={"multipart/form-data"})
	public ResponseEntity<UploadFileResponseDTO> pushExercise(@RequestParam("file") MultipartFile file, @RequestParam("userName") String userName){
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(MessageConst.FILE_DOWNLOAD).path(fileName).toUriString();
		return new ResponseEntity<UploadFileResponseDTO>(new UploadFileResponseDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize()),HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
		Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        	logger.error(MessageConst.ERROR_FILE_TYPE);
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}

}
