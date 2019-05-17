cafedevApp.controller('TestCtrl', ['$scope','$http','$routeParams','$timeout','AuthFactory', function($scope, $http,$routeParams,$timeout,authFactory){
    $(document).ready(function () {
        $("#progressBar").kendoProgressBar({
            min: 0,
            max: 10,
            type: "percent",
            complete: onComplete
        });
    });

    function onComplete(e) {

        $("#startProgress").text("Restart Progress").removeClass("k-state-disabled");
    }

    $("#startProgress").click(function () {
        if (!$(this).hasClass("k-state-disabled")) {
            $(this).addClass("k-state-disabled");
            progress();
        }
    });

    function progress() {
        var pb = $("#progressBar").data("kendoProgressBar");
        pb.value(0);

        var interval = setInterval(function () {
            if (pb.value() < 10) {
                pb.value(pb.value() + 1);
            } else {
                clearInterval(interval);
            }
        }, 100);
    }
  }]);

