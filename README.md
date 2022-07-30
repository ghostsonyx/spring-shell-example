# spring-shell-example
Kotlin Spring Shell Native Project

### **Work in Progress**
Example project to play around with spring shell with native capabilities.

`get-github-user-details --userName {userName}`

Example Response
```
User(
  login=ghostsonyx, 
  id=66806782, 
  url=https://api.github.com/users/ghostsonyx, 
  repos_url=https://api.github.com/users/ghostsonyx/repos, 
  name=Nathan Barron, 
  email=null, 
  followers=1,
  following=7, 
  created_at=2020-06-11, 
  updated_at=2022-07-06
)
```

`get-github-user-repos --userName {userName}`

Example Response
```
[
  Affirmations, 
  Dessert_Clicker, 
  Dice_Roller, 
  Happy_Birthday, 
  Lemonade, 
  Tip_Time, 
  todo-client, 
  todo-rest, 
  Words
]
```

`get-bitrise-user-details`

Important : make sure to add your bitrise authorization token to the applcation.properties file

Example Response
```
User(
  userName=ghostsonyx, 
  slug=de8a207def21dc0c,
  email=test1234@gmail.com,
  avatarUrl=null, 
  created=2022-07-30T16:51:21.52506Z,
  id=123456
)
```
