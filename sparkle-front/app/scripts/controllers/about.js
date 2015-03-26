'use strict';

/**
 * @ngdoc function
 * @name sparkleFrontApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the sparkleFrontApp
 */
angular.module('sparkleFrontApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
