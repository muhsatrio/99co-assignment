package routes

import (
	"user-service/controllers"
	"user-service/repositories"
	"user-service/services"

	"github.com/gin-gonic/gin"
)

func SetupRoutes(r *gin.Engine) {
	userRepo := repositories.NewUserRepository()
	userService := services.NewUserService(userRepo)
	userController := controllers.NewUserController(userService)

	userGroup := r.Group("/users")
	{
		userGroup.GET("", userController.GetUsers)
		userGroup.GET("/:id", userController.GetUser)
		userGroup.POST("", userController.CreateUser)
	}
}
