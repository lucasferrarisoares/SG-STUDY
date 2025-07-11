"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function HospitalizationListController($scope, $http, $location) {
    $scope.hospitalizationsList = [];
    $http.get("http://localhost:8080/hospitalizationsActive")
        .then(function (response) {
        $scope.hospitalizationsList = response.data;
    });
    $scope.voltar = function () {
        $location.path('/home');
    };
}
exports.default = HospitalizationListController;
