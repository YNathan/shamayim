app.controller('houses', ['$scope', '$http', '$filter', '$state', '$mdDialog', '$mdSidenav','ShamayimFunctions', function($scope, $http, $filter, $state, $mdDialog, $mdSidenav,ShamayimFunctions) {

     // Get value from the cookie
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1);
            if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
        }
        return "";
    }
    $scope.userName = getCookie("username");
    $scope.username = 'Developer';
    $scope.firstName = '';
    $scope.lastName = '';
    $scope.telephone = '052787878';
    $scope.email = 'ipsum@lorem.com';
    $scope.password = '';
    $scope.birthdate = '';
    $scope.userId = '';

    // For the houses
    $scope.houses = {
        availableDebtsOptions: [],
        selectedDebtsOption: {
            id: '1',
            Debtes: 'default'
        }
    };

    function showAlert(title, textContent, ariaLabel) {
        // Appending dialog to document.body to cover sidenav in docs app
        // Modal dialogs should fully cover application
        // to prevent interaction outside of dialog
        $mdDialog.show(
            $mdDialog.alert()
            .parent(angular.element(document.querySelector('#popupContainer')))
            .clickOutsideToClose(true)
            .title(title)
            .textContent(textContent)
            .ariaLabel(ariaLabel)
        );
    }


    // Will input the user name into the cookie
    function setUserNameCookie(szName, szValue) {
        document.cookie = szName + "=" + szValue + "; ";
    }


    // Get information conserning the user
    $http.get("/GET_LIST_OF_HOUSES")
        .then(function successCallback(response) {
                $scope.houses = response.data.houses
            },
            function error(response) {
                showAlert("Your attention please", response.data, "cant load houses");
            });


    $scope.uploadFile = function(files) {
        var fd = new FormData();
        //Take the selected file
        fd.append("file", files[0]);

        $http.post("/upload/" + getCookie("username"), fd, {
            withCredentials: true,
            headers: {
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        }).success(
            swal("Yeah!!!")
        ).error(
            swal("Oups! something wrong was hapening")
        );

    };
 // Language Section
    // For the Language
    $scope.Languages = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            HouseLanguage: 'default'
        }
    };
    $scope.Languages = ShamayimFunctions.getExistingLanguages();



    function getLanguage(szLanguageName) {
        // Get information conserning the house
        if(ShamayimFunctions.getLanguage(szLanguageName)== true)
        {
        $scope.dictionary = ShamayimFunctions.getDictionary();    
        }
        $scope.dictionary = ShamayimFunctions.getDictionary();

    }
    getLanguage("English");
    $scope.$watch('Languages.selectedOption', function (newVal, oldVal) {
            if (newVal != oldVal) {
                HouseLanguageName = newVal;
                getLanguage(newVal);

            }
        })
        // End Of Language Section

 $scope.toggleLeft = function() {
     $mdSidenav('left').toggle();
 }
 $scope.goToCopyright = function() {
     $state.go('Copyright');
 }
 $scope.goToUserInformation = function() {
     $state.go('userInformation');
 }
 $scope.goToHouses = function() {
     $state.go('Houses');
 }
 $scope.goToNewHouse = function() {
     $state.go('NewOrEditHouse');
 }
 $scope.goToHouse = function() {
     $state.go('House');
 }

}]);