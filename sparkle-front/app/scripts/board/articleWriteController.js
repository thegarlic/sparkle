'use strict';

angular.module('boardApp')
  .controller('ArticleWriteController', function ($scope, $http, $routeParams, $location) {
      
    var boardName = $routeParams.boardName;
    $scope.submit = function() {
      var req = {
        method: "POST",
        url: server + '/boards/' + boardName + '/articles/write',
        data: {
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
