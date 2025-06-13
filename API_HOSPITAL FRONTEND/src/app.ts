import * as angular from 'angular';

interface MeuScope extends angular.IScope {
  mensagem: string;
}

const app = angular.module('meuApp', []);

app.controller('MeuController', ['$scope', ($scope: MeuScope) => {
  $scope.mensagem = 'Olá, AngularJS com TypeScript!';
}]);
