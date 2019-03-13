package com.cafedev.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cafedev.common.MessageConst;
import com.cafedev.config.FileStorageProperties;
import com.cafedev.exception.FileStorageException;
import com.cafedev.exception.MyFileNotFoundException;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties){
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (IOException e) {
			throw new FileStorageException(MessageConst.ERROR_FILE_DIRECTORY, e);
		}
	}
	
	public String storeFile(MultipartFile file){
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			
			if(fileName.contains("..")){
				throw new FileStorageException(String.format(MessageConst.ERROR_FILE_NAME_INVALID, fileName));
			}
			
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return fileName;
		} catch (IOException e) {
			throw new FileStorageException(String.format(MessageConst.ERROR_FILE_NOT_STORED, fileName), e);
		}
	}
	
	public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException(String.format(MessageConst.ERROR_FILE_NOTFOUND, fileName));
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException(String.format(MessageConst.ERROR_FILE_NOTFOUND, fileName), ex);
        }
    }
}
