'use strict';

angular
  .module('boardApp', [
    'ngResource',
    'ngRoute'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/boardName/page', {
        redirectTo: 'boardName/page/1'
      })
      .when('/boardName/page/:pageNumber', {
        templateUrl: 'views/board/articlePage.html',
        controller: 'ArticlePageController'
      })
      .when('/boardName/write', {
        templateUrl: 'views/board/articleWrite.html',
        controller: 'ArticleWriteController'
      })
      .when('/boardName/:articleId', {
        templateUrl: 'views/board/articleRead.html',
        controller: 'ArticleReadController'
      })
      .when('/boardName/:articleId/edit', {
        templateUrl: 'views/board/articleEdit.html',
        controller: 'ArticleEditController'
      })
      .otherwise({
        redirectTo: 'boardName/page/1'
      });
  });

