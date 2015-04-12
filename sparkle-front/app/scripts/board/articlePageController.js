'use strict';

angular.module('boardApp')
  .controller('ArticlePageController', function ($scope, $http, $routeParams) {

    var pageNumber = $routeParams.pageNumber;
    var boardName = $routeParams.boardName;

    $scope.boardName = boardName;
    $scope.pageNumber = pageNumber;

    $http.get(serverUrl + '/boards/' + boardName + '/articles/page/' + pageNumber)
      .success(function(res, status, headers, config) {
        if(res.meta.ok) {
          $scope.list = res.data.articles;
          $scope.prevPage = res.data.articles.first ? 1 : parseInt(pageNumber, 10) - 1;
          $scope.nextPage = res.data.articles.last ? res.data.articles.totalPages : parseInt(pageNumber, 10) + 1;
          $scope.boardTitle = res.data.board.title;
        }
      })
      .error(function(res, status, headers, config) {
        alert(res.meta.message);
      });
  });
