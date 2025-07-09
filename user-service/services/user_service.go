package services

import (
	"user-service/models"
	"user-service/repositories"
)

type UserService interface {
	GetAllUsers(pageNum int, pageSize int) ([]models.User, error)
	GetUserByID(id uint) (models.User, error)
	CreateUser(user models.User) (models.User, error)
}

type userService struct {
	repo repositories.UserRepository
}

func NewUserService(r repositories.UserRepository) UserService {
	return &userService{repo: r}
}

func (s *userService) GetAllUsers(pageNum int, pageSize int) ([]models.User, error) {
	return s.repo.FindAll(pageNum, pageSize)
}

func (s *userService) GetUserByID(id uint) (models.User, error) {
	return s.repo.FindByID(id)
}

func (s *userService) CreateUser(user models.User) (models.User, error) {
	return s.repo.Create(user)
}
