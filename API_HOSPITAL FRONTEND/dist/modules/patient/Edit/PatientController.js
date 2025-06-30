"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function PatientController($scope, $http, $routeParams, $location) {
    $scope.patient = null;
    $http.get('http://localhost:8080/patients/' + $routeParams.id)
        .then(function (response) {
        $scope.patient = response.data;
    });
    $scope.saveEdicao = function () {
        $http.put('http://localhost:8080/patients/' + $scope.patient.cdPatient, { dePatient: $scope.patient.dePatient })
            .then(function () {
            $location.path('/patient');
        }, function () {
            alert('Erro ao editar paciente!');
        });
    };
    $scope.voltar = function () {
        $location.path('/patient');
    };
}
exports.default = PatientController;
