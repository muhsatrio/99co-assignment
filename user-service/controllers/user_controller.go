package controllers

import (
	"net/http"
	"strconv"

	"user-service/models"
	"user-service/services"

	"github.com/gin-gonic/gin"
)

type userResponse struct {
	ID        uint   `json:"id"`
	Name      string `json:"name"`
	CreatedAt int64  `json:"created_at"`
	UpdatedAt int64  `json:"updated_at"`
}

type UserController struct {
	service services.UserService
}

func NewUserController(s services.UserService) *UserController {
	return &UserController{service: s}
}

func (ctrl *UserController) GetUsers(c *gin.Context) {
	var pageNumInt = 1
	var pageSizeInt = 10
	var err error

	pageNum := c.Query("page_num")
	pageSize := c.Query("page_size")

	if pageNum != "" {
		pageNumInt, err = strconv.Atoi(pageNum)
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{
				"result": false,
				"error":  "Invalid page_num",
			})
			return
		}
	}

	if pageSize != "" {
		pageSizeInt, err = strconv.Atoi(pageSize)
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{
				"result": false,
				"error":  "Invalid page_size",
			})
			return
		}
	}
	users, err := ctrl.service.GetAllUsers(pageNumInt, pageSizeInt)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{
			"result": false,
			"error":  err.Error(),
		})
		return
	}

	var userResponses []userResponse

	for _, user := range users {
		userResponses = append(userResponses, userResponse{
			ID:        user.ID,
			Name:      user.Name,
			CreatedAt: user.CreatedAt.Unix(),
			UpdatedAt: user.UpdatedAt.Unix(),
		})
	}

	c.JSON(http.StatusOK, gin.H{
		"result": true,
		"users":  userResponses,
	})
}

func (ctrl *UserController) GetUser(c *gin.Context) {
	id, _ := strconv.Atoi(c.Param("id"))
	user, err := ctrl.service.GetUserByID(uint(id))
	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{
			"result": false,
			"error":  "User not found",
		})
		return
	}
	c.JSON(http.StatusOK, gin.H{
		"result": true,
		"user": userResponse{
			ID:        user.ID,
			Name:      user.Name,
			CreatedAt: user.CreatedAt.Unix(),
			UpdatedAt: user.UpdatedAt.Unix(),
		},
	})
}

func (ctrl *UserController) CreateUser(c *gin.Context) {
	var input models.User
	if err := c.ShouldBindJSON(&input); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{
			"result": false,
			"error":  err.Error(),
		})
		return
	}
	user, err := ctrl.service.CreateUser(input)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{
			"result": false,
			"error":  err.Error(),
		})
		return
	}
	c.JSON(http.StatusCreated, gin.H{
		"result": true,
		"user": userResponse{
			ID:        user.ID,
			Name:      user.Name,
			CreatedAt: user.CreatedAt.Unix(),
			UpdatedAt: user.UpdatedAt.Unix(),
		},
	})
}
