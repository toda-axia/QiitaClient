package com.wumpuss.qiitaclient.model

class Bookmark {
    companion object {
        fun createBookmarkModel(id: String, title: String, url: String, profileImage: String): Bookmark {
            return Bookmark().apply {
                this.id = id
                this.title = title
                this.url = url
                this.profileImage = profileImage
            }
        }
    }

    var id = ""
    var title = ""
    var url = ""
    var profileImage = ""
}