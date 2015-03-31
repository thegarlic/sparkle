'use strict';

angular
  .module('boardApp', [
    'ngResource',
    'ngRoute'
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
      .when('/boards/:boardName/:articleId/edit', {
        templateUrl: 'views/board/articleEdit.html',
        controller: 'ArticleEditController'
      })
      .otherwise({
        redirectTo: '/boards/free/articles/page/1'
      });
  });

