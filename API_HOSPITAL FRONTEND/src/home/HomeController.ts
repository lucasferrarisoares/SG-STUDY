export default function HomeController($scope, $location) {
  $scope.goHome = function() {
    $location.path('/home');
  };
}