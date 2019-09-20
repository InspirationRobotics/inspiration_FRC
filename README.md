# Inspiration FRC

## Setup

### Repository

```
[navigate to the directory you want to put this project in]
mkdir inspiration-FRC && cd inspiration-FRC
git clone https://www.github.com/InspirationRobotics/inspiration_FRC.git
```  

You can use gradle commands to deploy the program to the RoboRIO. To deploy the program, setup the development environment as described in the FRC control system tutorial, and then build and deploy through *Visual Studio Code* or use the Gradle from the commandline.

### Environment

Follow the instructions described in the documentation on this article: [WPILib Screensteps Live](https://wpilib.screenstepslive.com/s/4485) > Java Programming/C++ Programming.  

- Install Visual Studio Code
- Download the WPILib release and move the contents to a folder named frc2019 in your home (~) directory.
- Run the ToolsUpdater.py script: `python ToolsUpdater.py`
- Install the VSCode Extensions
- Setup VS Code so that it uses Java 11  

To install the CTRE libraries, download the and run the installer (on Windows) or download and manually install the libraries (on MacOS/Linux) from the [TalonSRX Technical Resources Page](http://www.ctr-electronics.com/talon-srx.html#product_tabs_technical_resources). To enable the libraries, right click on the build.gradle file on Visual Studio Code and click *Manage Vendor Libraries*. Select the option to install new libraries from an offline source, and click CTRE Pheonix.

### Additional notes

A script has been provided (run.sh) to build and deploy the program from the commandline if you want to use a command-line editor or an IDE besides Visual Studio Code.