# GitHubUserSDK

## Overview
Provide APIs to facilitate the App in obtaining a list of GitHub users and user information.

(Support pagination)

## API
### Response
All APIs will return a sealed class `GitHubResponse`, allowing the App to handle the response appropriately based on whether it is `Success` or `Error`.

For example:
```kotlin
// use case
val response: GitHubResponse<Users> = api()
when (response) {
    is GitHubResponse.Error -> Log.e(TAG, response.message)
    is GitHubResponse.Success -> {
        val users = response.data
        // do something
    }
}
```

### getUsers
#### Description
Reference: [list users](https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#list-users)

Fetch a list of GitHub users.
#### Parameters
- `authorization`: String - Your GitHub authorization token.
- `since`: Int (Optional, default = 0) - The user ID to start the list from.
- `perPage`: Int (Optional, default = 30) - The number of users to return per page.

### getUsersByName
#### Description
Reference: [search users](https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#search-users)

Fetch a list of GitHub users by the user name.

#### Parameters
- `authorization`: String - Your GitHub authorization token.
- `name`: String - The name of the user to search for.
- `page`: Int (Optional, default = 1) - The page number of the search results to retrieve.
- `perPage`: Int (Optional, default = 30) - The number of users to return per page.

### getUserInfo
#### Description
Reference: [get a user](https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#get-a-user)

Retrieve detailed information for a specific GitHub user.

#### Parameters
- `authorization`: String - Your GitHub authorization token.
- `userName`: String - The id of the user to retrieve.

## Error Handling
All methods are designed to handle common HTTP and network errors. 

Ensure your application is prepared to handle instances of IOException and HttpException as they indicate network issues and non-successful HTTP responses, respectively.

## Example
```kotlin
val apiManager = GitHubApiManager()
val usersResponse = apiManager.getUsers("your_auth_token")
when (response) {
    is GitHubResponse.Error -> Log.e(TAG, response.message)
    is GitHubResponse.Success -> {
        val users = response.data
        // do something
    }
}
```

## Testing
In `GitHubApiManagerTest`, ensure that all APIs are functioning correctly.

**Before running the tests, remember to replace `apiKey` with your own API key in `GitHubApiManagerTest`.**

## Installation
Include jitpack in your root settings.gradle file:
```gradle
pluginManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
And add the dependency to your app level build.gradle file:
```gradle
dependencies {
    implementation("com.github.s2g090123:Authme:v1.0.0")
}
```
