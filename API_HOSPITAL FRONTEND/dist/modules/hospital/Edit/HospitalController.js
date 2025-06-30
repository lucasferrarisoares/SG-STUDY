"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function HospitalController($scope, $http, $routeParams, $location) {
    $scope.hospital = null;
    $http.get('http://localhost:8080/hospitals/' + $routeParams.id)
        .then(function (response) {
        $scope.hospital = response.data;
    });
    $scope.saveEdicao = function () {
        $http.put('http://localhost:8080/hospitals/' + $scope.hospital.cdHospital, { deHospital: $scope.hospital.deHospital })
            .then(function () {
            $location.path('/hospital');
        }, function () {
            alert('Erro ao editar hospital!');
        });
    };
    $scope.voltar = function () {
        $location.path('/hospital');
    };
}
exports.default = HospitalController;
