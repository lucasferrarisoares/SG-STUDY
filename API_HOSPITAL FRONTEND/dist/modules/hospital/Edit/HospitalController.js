"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function HospitalController($scope, $http, $routeParams, $location) {
    $scope.hospital = null;
    // Buscar hospital pelo ID
    $http.get('http://localhost:8080/hospitals/' + $routeParams.id)
        .then(function (response) {
        $scope.hospital = response.data;
    });
    // Salvar edição
    $scope.salvarEdicao = function () {
        $http.put('http://localhost:8080/hospitals/' + $scope.hospital.cdHospital, { deHospital: $scope.hospital.deHospital })
            .then(function () {
            $location.path('/hospital');
        }, function () {
            alert('Erro ao editar hospital!');
        });
    };
    // Voltar sem salvar
    $scope.voltar = function () {
        $location.path('/hospital');
    };
}
exports.default = HospitalController;
