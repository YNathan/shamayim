app.controller('Copyright', ['$scope', '$mdSidenav', 'ShamayimFunctions', '$state', function ($scope, $mdSidenav, ShamayimFunctions, $state) {

    $scope.userName = ShamayimFunctions.getCookie("username");

    $scope.toggleLeft = function () {
        $mdSidenav('left').toggle();
    }
    $scope.goToCopyright = function () {
        $state.go('Copyright');
    }
    $scope.goToUserInformation = function () {
        $state.go('userInformation');
    }
    $scope.goToHouses = function () {
        $state.go('Houses');
    }
    $scope.goToNewHouse = function () {
        $state.go('NewOrEditHouse');
    }
    $scope.goToHouse = function () {
        $state.go('House');
    }

}]);
