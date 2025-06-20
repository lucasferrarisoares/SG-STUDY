"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function hWingController($scope, $http, $location) {
    $scope.hospitals = [];
    $scope.wings = [];
    $scope.newHwing = {};
    $scope.specialties = [];
    $scope.listhWing = function () {
        $http.get('http://localhost:8080/hwings')
            .then(function (response) {
            $scope.wings = response.data;
        });
    };
    $scope.addhWing = function () {
        if ($scope.newHwing.cdSpecialty && $scope.newHwing.cdHospital) {
            $http.post('http://localhost:8080/hwings', $scope.newHwing)
                .then(function () {
                $scope.newHwing = { cdSpecialty: '', cdHospital: '' };
                $scope.listhWing();
            });
        }
    };
    $scope.removerhWing = function (cdHWing) {
        $http.delete('http://localhost:8080/hwings/' + cdHWing)
            .then(function () {
            $scope.listhWing();
        });
    };
    $scope.editarhWing = function (cdHWing) {
        $location.path('/hwing/' + cdHWing + '/editar');
    };
    $scope.listHospitals = function () {
        $http.get('http://localhost:8080/hospitals')
            .then(function (response) {
            $scope.hospitals = response.data;
        });
    };
    $scope.listSpecialties = function () {
        $http.get('http://localhost:8080/specialties')
            .then(function (response) {
            $scope.specialties = response.data;
        });
    };
    // Inicialização
    $scope.listSpecialties();
    $scope.listHospitals();
    $scope.listhWing();
}
exports.default = hWingController;
