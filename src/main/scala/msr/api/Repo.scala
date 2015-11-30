package msr.api

case class Repo(
  id: Int,
  owner: User,
  name: String,
  full_name: String,
  description: String,
  url: String,
  subscribers_count: Int,
  stargazers_count: Int,
  watchers_count: Int
)
