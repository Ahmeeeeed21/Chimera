<?php

namespace App\Form;

use App\Entity\PublicationPsy;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class PublicationPsyType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('type',ChoiceType::class,
                array('choices'=>array(
                    'depression'=>'depression',
                    'socialisation'=>'socialisation',
                    'integration pro'=>'integration pro',
                ) ))
            ->add('img')
            ->add('description')
            ->add('sujet')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => PublicationPsy::class,
        ]);
    }
}
