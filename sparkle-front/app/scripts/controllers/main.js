'use strict';

/**
 * @ngdoc function
 * @name sparkleFrontApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sparkleFrontApp
 */
angular.module('sparkleFrontApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
