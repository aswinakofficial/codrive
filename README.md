# codrive

A carpooling app for Android. A driver publishes a trip with the route, vehicle, seats available and total cost. Other people find trips going their way and join them. Built in 2023 as a college project.

## Features

- **Create a trip**: pick the start and destination with Google Places autocomplete, then add the vehicle type and model, seats available and total cost
- **Find and join trips**: browse published trips and join one
- **Manage trips**: see the trips you've created and the ones you've joined
- **Accounts**: sign up, log in, reset your password, and a profile page

## Stack

Java on Android, Material Components, Firebase Authentication and Realtime Database, Google Places API for location search, and Retrofit and Glide.

## Run it

1. Open the project in Android Studio (Arctic Fox or newer). It targets SDK 33, with a minimum of SDK 23.
2. Create your own Firebase project with Authentication and Realtime Database, and replace `app/google-services.json` with yours.
3. Add your own Google Places API key where the app calls the Places API (`models/PlaceApi.java`).
4. Build and run on an emulator or device.

---

Part of [Aswin AK's projects](https://aswin.xpar.in/projects/).
