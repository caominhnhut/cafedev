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
	}).when('/assignment/:id', {
		templateUrl : 'views/assignment.html',
		controller : 'AssignmentCtrl'
	}).when('/examination/:id', {
		templateUrl : 'views/examination.html',
		controller : 'ExaminationCtrl'
	}).when('/chart', {
		templateUrl : 'views/chart.html',
		controller : 'ChartCtrl'
	}).when('/setting', {
		templateUrl : 'views/setting.html',
		controller : 'SettingCtrl'
	}).when('/add-feed', {
		templateUrl : 'views/add-feed.html',
		controller : 'AddFeedCtrl'
	})
	.otherwise({
		redirectTo : '/'
	});
} ]);