"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function BedLogController($scope, $routeParams, $http, $location) {
    $scope.beds = [];
    $http.get("http://localhost:8080/bedsHistory/" + $routeParams.id)
        .then(function (response) {
        $scope.beds = response.data;
    });
    $scope.voltar = function () {
        $location.path('/bed');
    };
}
exports.default = BedLogController;
