/// <reference types="angular-route" />
import * as angular from 'angular';
import 'angular-route';

const app = angular.module('meuApp', ['ngRoute']);

app.config(['$routeProvider', function($routeProvider: ng.route.IRouteProvider) {
  $routeProvider
    .when('/home', {
      templateUrl: 'src/home/home.html',
      controller: 'MeuController'
    })
    .otherwise({
      redirectTo: '/home'
    });
}
]);

app.controller('MeuController', ['$scope', function($scope) {
  $scope.mensagem = 'Bem-vindo à Home!';
}]);