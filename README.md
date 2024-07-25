# FancyEssentials

## Introduction
FancyEssentials is a plugin designed for Minecraft servers that offers a range of useful commands such as kicking players, banning players, viewing player information, and more. This plugin aims to simplify server management and enhance the player experience.

## Features
- **Simplify Management**: By providing a range of useful commands, FancyEssentials can simplify the daily tasks of server administrators.
- **Enhance Player Experience**: The commands in the plugin help players better understand server rules and improve their experience on the server.
- **Internationalization Support**: The plugin supports multiple languages, making it convenient for players from different language backgrounds to use.

## Requirements
- Spigot/Paper Minecraft Server version 1.20 or higher
- Java 17 or higher

## Installation
1. **Download the plugin**: Download the latest version of the FancyEssentials plugin from the GitHub repository.
2. **Place the plugin**: Place the downloaded `.jar` file in the `plugins` folder of your server.
3. **Restart the server**: Restart the server to load the new plugin.
4. **Use commands**: Use the `/help` command in the game to view the list of available commands and use them as described.

## Configuration
After installation, you can customize the plugin by editing the `config.yml` file in the `plugins/FancyEssentials` directory. Here is an example configuration:
```yaml
settings:
  language: en
  enable-kick: true
  enable-ban: true
  motd: "Welcome to the server!"
```

## Command Examples
- `/kick <player>`: Kicks a player from the server.
- `/ban <player>`: Bans a player from the server.
- `/playerinfo <player>`: Displays information about a player.

For a full list of commands, use `/help` in the game.

## FAQ
**Q: What should I do if the plugin is not working?**
A: Ensure that your server meets the plugin requirements and that the plugin is placed in the correct directory. Check the server logs for any error messages.

**Q: How do I change the language of the plugin?**
A: Edit the `config.yml` file and change the `langs` setting to your preferred language.

## Changelog
### Version 1.0.0
- Initial release with basic kick, ban, and info commands.

## Screenshots
None.

## Getting Help
- **View Documentation**: The project documentation contains detailed descriptions and usage instructions for all commands.
- **Community Support**: Submit issues or suggestions on the Issues page of the GitHub repository.
- **Contact Developers**: Reach out to the project maintainers through the project's GitHub page.

## Maintenance and Contribution
- **Project Maintainer**: The project is maintained by Deepak Chen.
- **Contributors**: Community members are welcome to contribute code, report issues, or suggest improvements.

## License
This project is licensed under the GPL v3 license. Please refer to the LICENSE file in the project for more information.