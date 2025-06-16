/// <reference types="angular-route" />

const app = angular.module('meuApp', ['ngRoute']);

app.config(['$routeProvider', function($routeProvider: ng.route.IRouteProvider) {
  $routeProvider
    .when('/hospital', {
      templateUrl: 'src/modules/Hospital/hospital.html',
      controller: 'HospitaisController'
    })
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

app.controller('HospitaisController', ['$scope', function($scope) {
    $scope.hospitais = [];
    $scope.novoHospital = '';

    $scope.adicionarHospital = function() {
        if ($scope.novoHospital) {
            $scope.hospitais.push($scope.novoHospital);
            $scope.novoHospital = '';
        }
    };

    $scope.listarHospitais = function() {
        // Aqui pode buscar de uma API, se quiser.
    };
}]);