"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function HomeController($scope, $location) {
    $scope.goHome = function () {
        $location.path('/home');
    };
}
exports.default = HomeController;
