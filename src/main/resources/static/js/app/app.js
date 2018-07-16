var cafedevApp = angular.module('cafedevApp', [ 'ngRoute', ]);

cafedevApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'views/home.html',
		controller : 'HomeCtrl'
	}).when('/forum', {
		templateUrl : 'views/forum.html',
		controller : 'ForumCtrl'
	}).when('/topic/:id', {
		templateUrl : 'views/topic.html',
		controller : 'TopicCtrl'
	}).when('/sub-topic/:id', {
		templateUrl : 'views/sub-topic.html',
		controller : 'SubTopicCtrl'
	}).otherwise({
		redirectTo : '/'
	});
} ]);