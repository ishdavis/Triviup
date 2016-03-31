# Triviup

## Tradeoffs and Limitations
- In the game-starting flow, we sacrificed teams being able to join the lobby in real-time. Instead, we used mock data for now. (We plan to implement this properly in later iterations.)
- In the flow for bar owners editing questions, we decided against using a search bar for immediate filtering of questions. Due to time constraints we decided to keep it a simple list view.  We would consider implementing the search and filter functionality later on, if time permits, but we do not consider it integral to the flow.
- We consider our cheat-breaking functionality (that is, docking points for users leaving the application) integral to our application, but we did not have the time to implement this feature in this iteration.

## Inportant Details:
- Right now the username and passwords are hard-coded. Since the user creation is outside the scope of our tasks, we did not implement that functionality in our prototype. To log in as a user, use the credentials: (username) “foo@example.com”, (password) “hello”. To log in as a bar owner, use the credentials: (username) “bar@example.com”, (password) “world”.

## Known Bugs:
- When editing questions, if the users go back, rather than cancel, all previous changes (adding new questions or deleting others) are undone.
- If the user starts a game five minutes from now, the user is taken to the lobby.  If the user  backs out and picks a new start time, say six minutes from now, a second timer is created, and when each timer hits zero (even the previous five minute timer), the useris pulled away to the leaderboard screen.

