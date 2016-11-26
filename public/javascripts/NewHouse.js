app.controller('neworedithouse', ['$scope', '$http', '$state', '$interval', '$mdDialog', '$mdSidenav', function($scope, $http, $state, $interval, $mdDialog, $mdSidenav) {
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
    // Just print kind of 'hay message'
    $scope.message = 'M. ' + getCookie("username");
    $scope.DebterName = "";
    $scope.house_id = "1";
    $scope.state = "ca";
    $scope.city = "la";
    $scope.street = "bafla";
    $scope.house_number = 32;
    $scope.house_kind = 3;
    $scope.number_of_rooms = 1;
    $scope.number_of_living_rooms = 1;
    $scope.number_of_kitchens = 1;
    $scope.number_of_bedrooms = 3;
    $scope.number_of_bathrooms = 5;
    $scope.location_kind = 5;
    $scope.comments = "nice area have fun";
    $scope.purchase_price = 0.0;
    $scope.treatment_fees = 21.2;
    $scope.renovation_fees = 54.5;
    $scope.divers_fees = 54.2;
    $scope.userName = getCookie("username");
    var houseName = "";
    var tempArr = [];
 var house = {
            "state": "ca",
            "city": "la",
            "street": "blalba",
            "house_number": "3",
            "house_kind": "1",
            "number_of_rooms": "3",
            "number_of_living_rooms": "1",
            "number_of_kitchens": "2",
            "number_of_bedrooms": "1",
            "number_of_bathrooms": "1",
            "location_kind": "dfg",
            "comments": "Nice area great place to have fun"
    }

    // For the house
    $scope.houses = {
        availableOptions: [],
        selectedOption: {
            id: '1',
            house: 'default'
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

    // Get information conserning the
    $http.get("/GET_LIST_OF_HOUSES")
        .then(function successCallback(response) {
                angular.forEach(response.data.houses, function(value, key) {
                    itemName = {
                        id: key,
                        house: value
                    }
                    tempArr.push(itemName);
                    $scope.houses.availableOptions.push(itemName.house);
                }, $scope.houses);
            },
            function error(response) {
                showAlert("Your attention please", response.data, "cant load houses");
            });

    function checkIfNeedConfirming() {
        // While Thread
        // Get information conserning the
        $http.get("/GET_LIST_OF_HOUSES")
            .then(function successCallback(response) {
                    angular.forEach(response.data.houses, function(value, key) {
                        itemName = {
                            id: key,
                            house: value
                        }
                        tempArr.push(itemName);
                        $scope.houses.availableOptions.push(itemName.house);
                    }, $scope.houses);
                },
                function error(response) {
                    showAlert("Your attention please", response.data, "cant load houses");
                });
    }
    $interval(checkIfNeedConfirming, 200000);
    $scope.setNewHouse = function() {
         house.state = $scope.state;
         house.city = $scope.city,
         house.street = $scope.street,
         house.house_number =  $scope.house_number;
         house.house_kind =  $scope.house_kind;
         house.number_of_rooms =  $scope.number_of_rooms;
         house.number_of_living_rooms =  $scope.number_of_living_rooms;
         house.number_of_kitchens =  $scope.number_of_kitchens;
         house.number_of_bedrooms =  $scope.number_of_bedrooms;
         house.number_of_bathrooms =  $scope.number_of_bathrooms;
         house.location_kind =  $scope.location_kind;
         house.comments = $scope.comments;


        var res = $http.post('/SET_NEW_HOUSE', house);
        res.success(function(data, status, headers, config) {
            alert(data);
        });
        res.error(function(data, status, headers, config) {
            alert("failure message: " + JSON.stringify({
                data: data
            }));
        });


    }

    // Logic methods section
    function getHouse(nHouseId) {
        // Get information conserning the house
        $http.get("/GET_HOUSE_BY_ID/" + nHouseId)
            .then(function successCallback(response) {
                    $scope.house_id = response.data.house.house_id;
                    $scope.state = response.data.house.state;
                    $scope.city = response.data.house.city;
                    $scope.street = response.data.house.street;
                    $scope.house_number = response.data.house.house_number;
                    $scope.house_kind = response.data.house.house_kind;
                    $scope.number_of_rooms = response.data.house.number_of_rooms;
                    $scope.number_of_living_rooms = response.data.house.number_of_living_rooms;
                    $scope.number_of_kitchens = response.data.house.number_of_kitchens;
                    $scope.number_of_bedrooms = response.data.house.number_of_bedrooms;
                    $scope.number_of_bathrooms = response.data.house.number_of_bathrooms;
                    $scope.location_kind = response.data.house.location_kind;
                    $scope.comments = response.data.house.comments;
                    $scope.purchase_price = response.data.house.purchase_price;
                    $scope.treatment_fees = response.data.house.treatment_fees;
                    $scope.renovation_fees = response.data.house.renovation_fees;
                    $scope.divers_fees = response.data.house.divers_fees;
                },
                function error(response) {
                    showAlert("Your attention please", response.data, "cant load houses");
                });
    }



    // Just check if there is a user name
    if (getCookie("username") == null) {
        // Go to the main application
        $state.go('wellcom');
    }

    $scope.$watch('houses.selectedOption', function(newVal, oldVal) {
        if (newVal != oldVal) {
            houseName = newVal;
            //alert(newVal.house_number + " " + newVal.street + " " + newVal.city + " " + newVal.state + " " + newVal.house_id);
            getHouse(newVal.house_id);

        }
    })

    // Update profile picture
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