$(function() {

    // ===== COMMENT ====
    $('form[name="comment-form"]').submit(function( event ) {

        event.preventDefault()
        var $self = $(this)
        var basePostId = $self.data("base-post-id")
        var $commentContent = $self.find('textarea[name="comment-content"]')
        var data = {
            basePostId: basePostId,
            content: $commentContent.val()
        }

        http('/post/comment', 'POST', data, {
            success: reload,
            error: function (r) {
                alert(r.message)
            }
        })


    })

    // ===== SHOW COMMENT FORM ====
    $('.add-comment-link').click(function () {
        var $self = $(this)
        $self.parent().parent().parent().find(".q-reply-form").show()
    })

    // ===== VOTE ====
    $('.q-vote-down-area, .q-vote-up-area').click(function () {
        var $self = $(this)
        var basePostId = $self.data("base-post-id")
        var voteType = $self.data("vote-type")

        var data = {
            basePostId: basePostId,
            voteType: voteType
        }

        http('/post/vote', 'POST', data, {
            success: function (r) {
                reload()
            },
            error: function (r) {
                alert(r.message)
            }
        })

    })

    function http(url, method, data, callback) {
        $.ajax({
            url: url,
            type: method,
            data: JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            headers: {
                "token": "6427d1eb-64be-45fd-b38d-cff870ef4362"
            },
            dataType: 'json',
            success: function (data) {
                callback.success && callback.success(data.responseText)
            },
            error: function (data) {
                callback.error && callback.error(JSON.parse(data.responseText))
            }
        })
    }

    function reload() {
        location.reload()
    }

})