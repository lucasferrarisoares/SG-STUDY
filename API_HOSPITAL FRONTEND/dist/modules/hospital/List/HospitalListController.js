"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function HospitalListController($scope, $http, $location) {
    $scope.hospitais = [];
    $scope.newHospital = { deHospital: '' };
    $scope.listHospitais = function () {
        $http.get('http://localhost:8080/hospitals')
            .then(function (response) {
            $scope.hospitais = response.data;
        });
    };
    $scope.addHospital = function () {
        if ($scope.newHospital.deHospital) {
            $http.post('http://localhost:8080/hospitals', $scope.newHospital)
                .then(function () {
                $scope.newHospital = { deHospital: '' };
                $scope.listHospitais();
            });
        }
    };
    $scope.removeHospital = function (cdHospital) {
        $http.delete('http://localhost:8080/hospitals/' + cdHospital)
            .then(function () {
            $scope.listHospitais();
        });
    };
    $scope.editHospital = function (cdHospital) {
        $location.path('/hospitais/' + cdHospital + '/editar');
    };
    $scope.listHospitais();
}
exports.default = HospitalListController;
