package repositories

import (
	"user-service/database"
	"user-service/models"
)

type UserRepository interface {
	FindAll(pageNum int, pageSize int) ([]models.User, error)
	FindByID(id uint) (models.User, error)
	Create(user models.User) (models.User, error)
}

type userRepo struct{}

func NewUserRepository() UserRepository {
	return &userRepo{}
}

func (r *userRepo) FindAll(pageNum int, pageSize int) ([]models.User, error) {
	var users []models.User
	offset := (pageNum - 1) * pageSize
	err := database.DB.Limit(pageSize).Offset(offset).Find(&users).Error
	return users, err
}

func (r *userRepo) FindByID(id uint) (models.User, error) {
	var user models.User
	err := database.DB.First(&user, id).Error
	return user, err
}

func (r *userRepo) Create(user models.User) (models.User, error) {
	err := database.DB.Create(&user).Error
	return user, err
}
