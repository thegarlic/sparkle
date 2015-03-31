'use strict';

angular.module('boardApp')
  .controller('ArticleReadController', function ($scope, $http, $routeParams) {
	  
	  var articleId = $routeParams.articleId;
	  
	  $http.get(server + '/boards/free/articles/' + articleId)
	  .success(function(data, status, headers, config) {
		  if(data.meta.ok) {
			  $scope.article = data.data;
		  }
	  })
	  .error(function(data, status, headers, config) {
		  alert(data.meta.message);
	  });
  });
