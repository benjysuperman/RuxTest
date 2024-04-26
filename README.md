# Rux Game Android App

## Overview

Rux Game is an Android application developed for Rux Robot with a round screen (640dp x 640dp), running on Android 11. Initially intended to interface with the Game API, this project has evolved into a proof of concept featuring an amusing loop card game showcasing various anime characters.

## Features

- **Anime Character Showcase**: Displays characters from various animes along with their pictures and names.
- **Character Interaction**: Users can interact with the characters, each having unique behaviors and actions.
- **Light and Motor Interaction**: Utilizes the robot's light signals and motor for interactive experiences.
- **Network Checking**: Ensures network availability before operation.
- **Full Screen Mode**: Runs in full-screen mode to enhance user immersion.

## Installation

To set up the project:

1. Clone the repository.
2. Open the project in Android Studio.
3. Ensure you are targeting Android 11 (API level 30) or above.
4. Sync the project with Gradle.
5. Run the app on a compatible device or emulator.

## Usage

On launching the app, it checks for network availability. If not connected, it will terminate. Otherwise, it will display the home screen character and enter full screen mode. Users can interact through touch to navigate between different characters and activate various actions like light changes and motor movements.

## Technical Details

- **Package Name**: `com.cybridz.ruxspotify` but should have been `com.cybridz.ruxgametest` instead
- **Main Components**:
    - `AnimCharacter`: Base class for characters with properties like name, light, and actions.
    - `DatasHelper`: Manages descriptions loaded from assets.
    - `NetworkHelper`: Checks for network availability.
- **Games Included**:
    - Test Game: Basic interactions with random colors and movements.
    - Anim Game: More detailed interactions based on selected anime character.

## Dependencies

- Android SDK: Target API Level 30 (Android 11).
- Robot SDK: Specific libraries related to Rux Robot functionalities.

## App Permissions

- Internet: For API interactions and checking network status.
- Full screen: To hide the system UI for better user engagement.

## Contributing

Feel free to fork the project and submit pull requests. You can also open issues for bugs or feature requests.

## Authors

- Benji
---

## Develop
Created a new branch for dev

Enjoy exploring the Rux Game app with its interactive and fun touchpoints! For any queries or further development ideas, keep the contributions flowing.
