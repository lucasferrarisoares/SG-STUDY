"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function PatientListController($scope, $http, $location) {
    $scope.patients = [];
    $scope.newPatient = { dePatient: '' };
    $scope.listPatients = function () {
        $http.get('http://localhost:8080/patients')
            .then(function (response) {
            $scope.patients = response.data;
        });
    };
    $scope.addPatient = function () {
        if ($scope.newPatient.dePatient) {
            $http.post('http://localhost:8080/patients', $scope.newPatient)
                .then(function () {
                $scope.newPatient = { dePatient: '' };
                $scope.listPatients();
            });
        }
    };
    $scope.removePatient = function (cdPatient) {
        $http.delete('http://localhost:8080/patients/' + cdPatient)
            .then(function () {
            $scope.listPatients();
        });
    };
    $scope.editPatient = function (cdPatient) {
        $location.path('/patient/' + cdPatient + '/editar');
    };
    $scope.internarPatient = function (cdPatient) {
        $location.path('/patients/' + cdPatient + '/internar');
    };
    $scope.listPatients();
}
exports.default = PatientListController;
