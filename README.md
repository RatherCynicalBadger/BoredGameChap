# BoredGameChap

**Disclaimer:**
_App is currently in early version, and as such it does not implement all intended features yet._

BoredGameChap is a simple app, that allow board game fans to create and join teams, organize meetings as well as manage their own game collection.

Additionally, users can create and vote in polls, attached by meeting creator with propositions of games to play.

The app, made in Spring Boot, features BGG XML API requests, allowing users to easily fetch info about games, that are not yet in app's MySQL database.

### Setup

At this moment, you have to configure your own local MySQL database and alter *application.properties* accordingly to create DB connection.
After that, you can use *init.sql*, which should create database schema with all tables, as well as insert values into _role_ table, which is necessary for Spring Security to function properly.

### Usage

Enter {localhost:XXXX}/home for homepage
Create user account using REGISTER option
On user dashboard you can manage your games, teams.
You can add game to your collection, which will redirect you to form, where you can choose, whether add game from internal DB, search for game through API or add game manually.
You can visit team dashboard from team list. From here, you can see upcoming meetings, create your own and optionally attach poll to it.
Group creator can send invites for other users through group admin panel. Invites should appear on user's dashboard where they can be accepted or rejected.