"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function PatientController($scope) {
    $scope.patients = [];
    $scope.newPatient = '';
    $scope.addPatient = function () {
        if ($scope.newPatient) {
            $scope.patients.push($scope.newPatient);
            $scope.newPatient = '';
        }
    };
    $scope.listPatients = function () {
        // busca por API
    };
}
exports.default = PatientController;
