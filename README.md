# Authme

## Overview
An app that retrieves a list of GitHub users, filters the list by id, and obtains detailed user information.

(Support pagination)

**Before use, a necessary step is to replace `API_KEY` with your own API key in `MainApplication`.**

All data is obtained through the [GitHubUserSDK](https://github.com/s2g090123/Authme/tree/main/GitHubUserSDK).

## Structure
[![](https://mermaid.ink/img/pako:eNo9j08LwjAMxb9KycmB4n0HQTf_gV4UvRgP0catuLaj6xBRv7uZwnIK-b28vLzg6jVDCkWgulSbHTolNT0hHNaIbpB5W_uGLhUnCGc1Gk3eCIvKP8b7SJER3mo2QDgafmzFqUJI_hazTqsyYYeGM2q4J9mP5EJ2LN4m-vDsYf6Dc7m_NHHVXqa12ZKjgoOchyFYDpaMlsSvbgEhlmzFO5VWU7gjoPuIjtro9093hTSGlofQ1lri5obkUQvpjaqmn851l-Gv_HwBkpVWGQ?type=png)](https://mermaid.live/edit#pako:eNo9j08LwjAMxb9KycmB4n0HQTf_gV4UvRgP0catuLaj6xBRv7uZwnIK-b28vLzg6jVDCkWgulSbHTolNT0hHNaIbpB5W_uGLhUnCGc1Gk3eCIvKP8b7SJER3mo2QDgafmzFqUJI_hazTqsyYYeGM2q4J9mP5EJ2LN4m-vDsYf6Dc7m_NHHVXqa12ZKjgoOchyFYDpaMlsSvbgEhlmzFO5VWU7gjoPuIjtro9093hTSGlofQ1lri5obkUQvpjaqmn851l-Gv_HwBkpVWGQ)

Utilizes the MVVM and Repository Pattern architecture, incorporating the following technologies:
- Jetpack Compose
- Coroutine & Coroutine Flow
- Navigation
- Paging3
- Koin

## Screen Recording

https://github.com/s2g090123/Authme/assets/32809761/b568fd7e-87fc-484a-8061-842f3897f417

## Testing
Include UI testing and unit testing.

### Unit Test
- Test that UserListViewModel correctly retrieves list data.
- Test that UserListViewModel correctly filters list data by id.
- Test that UserInfoViewModel correctly retrieves detailed user information.

### UI Test
Verify that the items in the list are displayed correctly.
After entering an id, check that the items in the list are displayed correctly.
Verify that the user's detailed information is displayed correctly.
