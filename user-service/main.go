package main

import (
	"user-service/database"
	"user-service/routes"

	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.Default()
	database.ConnectDB()
	routes.SetupRoutes(r)
	r.Run(":8000")
}
