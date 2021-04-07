



#### Github fork 后的项目，怎么与原项目同步更新？

```
1. 通过 git remote -v 查看是否存在远程仓库
2. 如果没有，需要设置远程仓库
	git remote add upstream https://github.com/repo_username/repo_name.git
3. 更新远程仓库：git fetch upstream
4. 切换到本地 master
5. git merge upstream/master
6. git push origin master
```

