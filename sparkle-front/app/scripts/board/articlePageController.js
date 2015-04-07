'use strict';

angular.module('boardApp')
  .controller('ArticlePageController', function ($scope, $http, $routeParams) {
          
    var pageNumber = $routeParams.pageNumber;
    var boardName = $routeParams.boardName;
          
    $scope.boardName = boardName;
    $scope.pageNumber = pageNumber;
          
    $http.get(server + '/boards/' + boardName + '/articles/page/' + pageNumber)
      .success(function(res, status, headers, config) {
        if(res.meta.ok) {
          $scope.list = res.data;
          $scope.prevPage = res.data.first ? 1 : parseInt(pageNumber, 10) - 1;
          $scope.nextPage = res.data.last ? res.data.totalPages : parseInt(pageNumber, 10) + 1;
        }
      })
      .error(function(res, status, headers, config) {
        alert(res.meta.message);
      });
  });
