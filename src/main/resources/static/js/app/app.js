var cafedevApp = angular.module('cafedevApp', [
  'ngRoute',
]);

cafedevApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/', {
        templateUrl: 'views/home.html',
        controller: 'HomeCtrl'
      }).
      when('/forum', {
        templateUrl: 'views/forum.html',
        controller: 'ForumCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);