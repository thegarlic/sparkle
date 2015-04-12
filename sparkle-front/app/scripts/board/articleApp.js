'use strict';

angular
  .module('boardApp', [
    'ngResource',
    'ngRoute',
    'angularMoment'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/boards/:boardName/articles/page', {
        redirectTo: '/boards/:boardName/articles/page/1'
      })
      .when('/boards/:boardName/articles/page/:pageNumber', {
        templateUrl: 'views/board/articlePage.html',
        controller: 'ArticlePageController'
      })
      .when('/boards/:boardName/articles/write', {
        templateUrl: 'views/board/articleWrite.html',
        controller: 'ArticleWriteController'
      })
      .when('/boards/:boardName/articles/:articleId', {
        templateUrl: 'views/board/articleRead.html',
        controller: 'ArticleReadController'
      })
      .when('/boards/:boardName/articles/:articleId/edit', {
        templateUrl: 'views/board/articleEdit.html',
        controller: 'ArticleEditController'
      })
      .otherwise({
        redirectTo: '/boards/free/articles/page/1'
      });
  }).filter('makeRange', function () {
    return function (input) {
      var lowBound, highBound;
      switch (input.length) {
        case 1:
          lowBound = 0;
          highBound = parseInt(input[0]) - 1;
          break;
        case 2:
          lowBound = parseInt(input[0]);
          highBound = parseInt(input[1]);
          break;
        default:
          return input;
      }
      var result = [];
      for (var i = lowBound; i <= highBound; i++)
        result.push(i);
      return result;
    };
  });
