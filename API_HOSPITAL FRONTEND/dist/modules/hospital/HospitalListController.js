"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function HospitalListController($scope, $http, $location) {
    $scope.hospitais = [];
    $scope.newHospital = { deHospital: '' };
    // Listar hospitais
    $scope.listHospitais = function () {
        $http.get('http://localhost:8080/hospitals')
            .then(function (response) {
            $scope.hospitais = response.data;
        });
    };
    // Adicionar hospital
    $scope.addHospital = function () {
        if ($scope.newHospital.deHospital) {
            $http.post('http://localhost:8080/hospitals', $scope.newHospital)
                .then(function () {
                $scope.newHospital = { deHospital: '' };
                $scope.listHospitais();
            });
        }
    };
    // Remover hospital
    $scope.removerHospital = function (cdHospital) {
        $http.delete('http://localhost:8080/hospitals/' + cdHospital)
            .then(function () {
            $scope.listHospitais();
        });
    };
    // Redirecionar para página de edição
    $scope.editarHospital = function (cdHospital) {
        $location.path('/hospitais/' + cdHospital + '/editar');
    };
    // Carrega a lista ao iniciar
    $scope.listHospitais();
}
exports.default = HospitalListController;
