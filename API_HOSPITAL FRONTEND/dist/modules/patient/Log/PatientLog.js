"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function PatientLogController($scope, $http, $routeParams, $location) {
    $scope.patient = null;
    $scope.logs = [];
    $scope.currentPage = 0;
    $scope.pageSize = 5;
    $scope.hasNextPage = false;
    // Buscar paciente
    $http.get('http://localhost:8080/patients/' + $routeParams.id)
        .then(function (response) {
        $scope.patient = response.data;
    });
    $scope.listLogs = function () {
        $http.get("http://localhost:8080/historicHospitalization/".concat($routeParams.id, "?page=").concat($scope.currentPage, "&size=").concat($scope.pageSize))
            .then(function (response) {
            $scope.logs = response.data.content;
            $scope.hasNextPage = !response.data.last;
        });
    };
    $scope.nextPage = function () {
        if ($scope.hasNextPage) {
            $scope.currentPage++;
            $scope.listLogs();
        }
    };
    $scope.previousPage = function () {
        if ($scope.currentPage > 0) {
            $scope.currentPage--;
            $scope.listLogs();
        }
    };
    $scope.voltar = function () {
        $location.path('/patient/' + $routeParams.id + '/internar');
    };
    $scope.listLogs();
}
exports.default = PatientLogController;
