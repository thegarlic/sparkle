'use strict';

angular.module('boardApp')
  .controller('ArticleReadController', function ($scope, $http, $routeParams) {

    var articleId = $routeParams.articleId;
    var boardName = $routeParams.boardName;
	  
    $http.get(server + '/boards/' + boardName + '/articles/' + articleId)
      .success(function(res, status, headers, config) {
        if(res.meta.ok) {
          $scope.article = res.data;
          $scope.boardName = boardName;
        }
      })
      .error(function(res, status, headers, config) {
        alert(res.meta.message);
      });
  });
