# Basic Command#

**git status**

查看当前工作区域状态(Stagine Area)

**git log**

查看提交的记录

**git remote add origin xx.git**
添加远程repo地址


**git diff**

当你pull文件，可以使用git diff 查看文件变更。
当使用git add ，将更改的文件提交到工作区域，同样可以使用此命令来查看更改。
git diff --staged

git diff brancha branchb

**git log -p**

显示commit的记录

参看分支文件不同点

**git rm 'xx.txt'**

删除文件

#Commit#

**git add**

将文件放到工作区域，还没有提交到你本地repo

git add '*.txt'

**git commit**

将工作区域的文件提交到本地repo

**git commit -a -m "xx"**

可以将add这步省略，直接commit

**git commit --amend -m""**

如果工作区域有提交新的内容，而你不想它作为一个新的commit，可以使用amend命令。进行提交

**git commit --fix-up commit_id**
通过 git log --oneline 查看commit历史。
当不想新增加commit的时候，就可以通过fix-up将新的更加添加到旧的更改中

**git push -u origin master**

**git reset file**

可以将工作区域的文件remove到。不是将文件删除，文件还是在你磁盘上。只是从工作区域中
移除了。

**git reset --soft HEAD^**
将commit到本地的roll back 到工作区域。
--soft 保留文件更改

**git reset --hard HEAD^**
同上，但是不保留文件更改

**git checkout --file**

可以将file roll back到上次commit的内容。
--避免不会意外的checkout了一个分支

提交本地repo到远程repo。
-u是告诉git记住这些parameters，以后就可以简单的使用git push

origin 是远程repo
master 是本地的默认分支


#Branch Command#
**git branch clean_up**

创建branch

**git checkout clean_up**

切换branch

**git checkout -b clean_up**

相当于上面两条命令，创建branch，切换branch

**git branch -D clean_up**
删除本地branch(-D 是delete force缩写)

**git checkout -b cleanup remote-stream/master
根据远程分支创建新的分支

**git merge clean_up**

在master分支下，将clean_up分支合并到master分支

**git branch -d clean_up**

删除分支

**git push origin --delete branch_name**
或者
**git push origin :<branch_name>**
删除远程分支，远程删除掉了，本地并没有删除

**git remote show origin**
查看远程信息

**git remote prune origin**
远程分支已经删除，本地分支还在引用。清除引用

**git push origin clean_up**

提交本地clean_up branch 到远程clean_upbranch

**git branch -r**

查看远程branch

**git fetch**

pull remote 的branch

**git rebase**

git pull 可以直接将本地的repo更新到远程repo.
也可以使用git fetch查看更改，然后使用git rebase 

**git rebase --continue**

rebase 过程中有冲突，需要解决冲突，然后使用git add ，将文件添加到
工作区域，然后使用git rebase --continue
#Tag#

**git add -a "v1.3.2" -m "new version"**

创建新的tag,-a表示tag有一个注解

**git push --tags"

提交创建的tag到远程repo

**git checkout -t tag_name**

回滚到特定的tag版本
#Config#

**git config --global user.name myname**

**git log --pretty=oneline**

git log 默认是meduim,会有很多的信息，包括author等信息。可以使用oneline来简化显示
会覆盖GIT_AUTHOR_NAME,和GIT_COMMITTER_NAME环境变量

**git config alias.amaingcode commit**

给git commit 起个别名

**git blame index.html**

查看文件的作者，修改信息

#Conflict#

HEAD 是你本地的，需要保留下来的

	`<<<<<<< HEAD
        <li><a href="cat.html">Cats</a></li>
        <li><a href="dog.html">Dogs</a></li>
     =======
        <li><a href="cat.html">Felines</a></li>
        <li><a href="dog.html">Canines</a></li>
    >>>>>>> 6a487f9eb0e0a5110bdf2a45a4f5dbcc3d4eec53`
