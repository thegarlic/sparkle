'use strict';

angular.module('boardApp')
  .controller('ArticleEditController', function ($scope, $http, $routeParams, $location) {

      var boardName = $routeParams.boardName;
      var articleId = $routeParams.articleId;

      $http.get(serverUrl + '/boards/' + boardName + '/articles/' + articleId)
        .success(function(res, status, headers, config) {
          if(res.meta.ok) {
            $scope.title = res.data.title;
            $scope.author = res.data.author;
            $scope.text = res.data.text;
          }
        })
        .error(function(res, status, headers, config) {
          alert(res.meta.message);
        });

      $scope.submit = function() {
        var req = {
          method: "PUT",
          url: serverUrl + '/boards/' + boardName + '/articles/' + articleId,
          data: {
            id: articleId,
            title: $scope.title,
            text: $scope.text,
            author: $scope.author
          }
        };

        $http(req)
          .success(function(res, status, headers, config) {
            if(res.meta.ok) {
              $location.path('/boards/' + boardName + '/articles/' + res.data.id);
            }
          })
          .error(function(res, status, headers, config) {
            alert(res.meta.message);
          });
      };
  });
